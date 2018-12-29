import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Hotel} from "../hotel";

@Injectable(
)
export class SystemAdminService {

  constructor(private http: HttpClient) {
  }

  public getHotels(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>("/api/public/hotels");
  }
  public addHotel(hotel: Hotel): Observable<Hotel> {
    return this.http.post<Hotel>("api/public/hotels",hotel);
  }
}
