import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Hotel} from "../hotel";

@Injectable()
export class HotelsService {
  constructor(private http: HttpClient) {

  }
  public getHotels(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>("/api/public/hotels");
  }
}
