import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../user.service";
import {User} from "../user";


@Component({
  selector: 'profile',
  templateUrl: './user.profile-editor.component.html'
})

export class UserProfileEditorComponent implements OnInit {
  private id: string;

  memento: User = null;
  user: User = User.empty();

  editable: boolean = false;
  editing: boolean = false;

  constructor(private route: ActivatedRoute, private userService: UserService) {
    this.id = route.snapshot.paramMap.get('id');

    if (this.id == null) {
      this.id = "me";
    }

    if (this.id === 'me') {
      this.editable = true;
    }

    userService.getUser(this.id).subscribe(u => this.user = User.clone(u));
  }

  ngOnInit(): void {
  }

  edit(): void {
    if (this.editable) {
      this.editing = true;
      this.memento = User.clone(this.user);
    }
  }

  stopEdit(): void {
    if (this.editing) {
      this.editing = false;
      this.memento = null;
    }
  }

  save(): void {
    if (this.editable && this.editing) {
      this.userService.updateProfile(this.user).subscribe(value => {
        this.stopEdit();
      });
    }
  }

  cancel(): void {
    if (this.editing) {
      this.user = this.memento;
      this.stopEdit();
    }
  }


}
