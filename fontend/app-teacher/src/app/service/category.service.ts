import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private url = 'http://localhost:8020/categories/';

  async getCategoryById(id: string) {
    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var requestOptions = {
      method: 'GET',
      headers: myHeaders,
    };

    return await fetch(this.url + id, requestOptions);
  }
}
