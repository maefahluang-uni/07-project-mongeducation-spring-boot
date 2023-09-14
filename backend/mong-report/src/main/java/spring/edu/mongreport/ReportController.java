package spring.edu.mongreport;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ServerMapper serverMapper;

    // non-relational relationship of restful API (classic). 

    @GetMapping("/reports")
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return ResponseEntity.ok(reports);
    }

     @GetMapping("/reports/{id}")
    public ResponseEntity<?> getReportById(@PathVariable Long id) {
        Optional<Report> reportOpt = reportRepository.findById(id);

        if (!reportOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("report not found.");
        }

        Report report = reportOpt.get();
        return ResponseEntity.ok(report);
    }

    @PostMapping("/reports")
    public ResponseEntity<String> createReports(@RequestBody Report report) {
        reportRepository.save(report);
        return ResponseEntity.ok("report created.");
    }

    @PutMapping("/reports/{id}")
    public ResponseEntity<String> updateReports(@PathVariable Long id, @RequestBody Report report) {
         if (!reportRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("report not found.");
        }

        reportRepository.save(report);
        return ResponseEntity.ok("report updated.");
    }

    @PatchMapping("/reports/{id}")
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

    @DeleteMapping("/reports/{id}")
    public ResponseEntity<String> deleteReportById(@PathVariable Long id) {
        if (!reportRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("report not found.");
        }

        reportRepository.deleteById(id);
        return ResponseEntity.ok("report deleted.");
    }

    @DeleteMapping("/reports")
    public ResponseEntity<String> deleteAllStudents() {
        reportRepository.deleteAll();
        return ResponseEntity.ok("all reports deleted.");
    }

}
