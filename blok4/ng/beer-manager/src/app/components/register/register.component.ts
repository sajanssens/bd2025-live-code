import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {GENS} from '../../model/contact/gender';
import {LANGS, Language} from '../../model/contact/language';
import {emailValidator} from '../../util/util';
import {ContactService} from '../../services/contact.service';
import {Contact} from '../../model/contact/contact';


@Component({
  selector: 'bm-register',
  templateUrl: './register.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  genders = GENS
  languages = LANGS

  contactForm!: FormGroup
  contact = {gender: {}} as Contact
  defaultGender = 1

  constructor(
    private contactService: ContactService,
    private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.initForm();
    this.contact.gender.id = 1
  }

  private initForm() {
    this.contactForm = this.fb.group({
      firstName: this.fb.control(''),
      surname: this.fb.control(''),
      email: this.fb.control('', [Validators.required, emailValidator]),
      gender: this.fb.control(''),
      languages: this.fb.array([])
    });

    this.initLanguagesControls();
  }

  get languagesFormArray(): FormArray {
    return this.contactForm.get('languages') as FormArray;
  }

  private initLanguagesControls(): void {
    this.languages.forEach(() => this.languagesFormArray.push(new FormControl()));
  }

  submit(): void {
    this.contactForm.value.languages = this.contactForm.value.languages
      .map((checked: boolean, i: number) => checked ? this.languages[i] : null)
      .filter((lang: Language) => lang !== null);

    this.contactService.add(this.contactForm.value);
    this.contactForm.reset();
  }

  get email() {
    return this.contactForm.get('email')
  }
}
