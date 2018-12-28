import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Hotel} from "../hotel";

@Injectable(
)
export class SystemAdminService {

  private hotels : Hotel[];

  constructor(private http: HttpClient) {
    this.getHotels().subscribe(hotels => this.hotels = hotels);
  }
  public getHotels(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>("/hotels");
  }
}
