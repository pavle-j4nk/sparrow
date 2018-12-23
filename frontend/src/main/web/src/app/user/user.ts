import {Role} from "./role";

export class User {


  constructor(public email: string = ""
    , public firstName: string = ""
    , public lastName: string = ""
    , public address: string = ""
    , public role: Role = Role.USER) {
  }

  public set(user: User): void {
    this.email = user.email;
    this.firstName = user.firstName;
    this.lastName = user.lastName;
    this.address = user.address;
    this.role = user.role;
  }

  public static clone(user: User): User {
    return new User(user.email, user.firstName, user.lastName, user.address, user.role);
  }

  public static empty() : User {
    return new User();
  }

}
