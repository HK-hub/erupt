import {Component, Input, OnInit} from '@angular/core';
import {Edit, EruptFieldModel} from "../model/erupt-field.model";
import {ChoiceEnum, EditType} from "../model/erupt.enum";
import {BsLocaleService, listLocales} from "ngx-bootstrap";
import {EruptModel} from "../model/erupt.model";
import {MatDatepickerInputEvent} from "@angular/material";
import * as moment from "moment";
@Component({
  selector: 'erupt-edit-type',
  templateUrl: './edit-type.component.html',
  styleUrls: ['./edit-type.component.scss']
})
export class EditTypeComponent implements OnInit {

  @Input() eruptFieldModels: Array<EruptFieldModel>;

  editType = EditType;

  choiceEnum = ChoiceEnum;

  edit: Edit;

  basicColor = "#fff";

  zhDate = "YYYY-MM-DD";
  zhDateTime = "YYYY-MM-DD HH:mm:ss";


  dateChange(event: MatDatepickerInputEvent<Date>, field: EruptFieldModel) {
    field.eruptFieldJson.edit.$value = event.value;
    console.log(field.eruptFieldJson.edit.$value);
    console.log(moment(event.value).format(this.zhDateTime).toString());
  }

  addValue(value) {
    alert(value);
  }

  clearValue(field: EruptFieldModel, event: Event) {
    if (event) {
      event.stopPropagation();
    }
    field.eruptFieldJson.edit.$value = null;
  }

  checkBoxChange = function ($event, val) {
    this.edit.$value = [];
    console.log($event.check());
    if ($event.check()) {
      this.edit.$value.push(val);
    } else {

    }
  };

  constructor(private localeService: BsLocaleService) {
    this.localeService.use("zh-cn");
  }

  ngOnInit() {

  }

}