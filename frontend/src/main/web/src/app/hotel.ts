import {User} from "./user/user";

export class Hotel {

  constructor(public id: number = -1
  , public name : string = ''
  , public description : string = ''
  , public userEmail : string = ''
  , public admin : User = new User()) {}

  public set(hotel : Hotel): void {
    this.id = hotel.id;
    this.name = hotel.name;
    this.description = hotel.description;
    this.userEmail = hotel.userEmail;
    this.admin = hotel.admin;
  }

  public static clone(hotel: Hotel): Hotel {
    return new Hotel(hotel.id, hotel.name, hotel.description, hotel.userEmail, hotel.admin);
  }

  public static empty(): Hotel {
    return new Hotel();
  }

}
