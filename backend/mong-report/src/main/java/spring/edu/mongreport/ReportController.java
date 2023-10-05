package spring.edu.mongreport;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import spring.edu.mongcourse.model.Course;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private RestTemplate restTemplate;
    private final String courseMicroserviceUrl = "http://localhost:8020";

    // non-relational relationship of restful API (classic). 

    @GetMapping()
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReportById(@PathVariable Long id) {
        Optional<Report> reportOpt = reportRepository.findById(id);

        if (!reportOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("report not found.");
        }

        Report report = reportOpt.get();
        return ResponseEntity.ok(report);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReports(@PathVariable Long id, @RequestBody Report report) {
         if (!reportRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("report not found.");
        }

        reportRepository.save(report);
        return ResponseEntity.ok("report updated.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchReports(@PathVariable Long id, @RequestBody ReportDTO reportDto) {
        Optional<Report> reportOpt = reportRepository.findById(id);

        if (!reportOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("report not found.");
        }

        Report report = reportOpt.get();
        serverMapper.updateReportFromDto(reportDto, report);
        reportRepository.save(report);
        
        return ResponseEntity.ok("report patched.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReportById(@PathVariable Long id) {
        if (!reportRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("report not found.");
        }

        reportRepository.deleteById(id);
        return ResponseEntity.ok("report deleted.");
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAllReports() {
        reportRepository.deleteAll();
        return ResponseEntity.ok("all reports deleted.");
    }


    // relational relationship of restful API (classic)

    public void createReportByEnroll(String student_id, String course_id) {
        ResponseEntity<Course> courseResponse = restTemplate.exchange(
            courseMicroserviceUrl + "/courses/{id}",
            HttpMethod.GET,
            null,
            Course.class,
            course_id
        );

        if (courseResponse.getStatusCode().is2xxSuccessful()) {
            Course course = courseResponse.getBody();
            if (course != null) {
                Report report = new Report();
                report.setStudentId(student_id);
                report.setCourseId(course_id);
                try {
                    reportRepository.save(report);
                    return;
                } catch (Exception ex) {
                    return;
                }
            } else {
                return;
            }
        }
    }

}
