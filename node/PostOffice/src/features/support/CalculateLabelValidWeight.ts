import {Given, Then, When} from "@cucumber/cucumber";
import {DeliveryType} from "../../models/DeliveryType";
import {Package} from "../../models/Package";
import {Label} from "../../models/Label";
import assert from "assert";
import {LabelService} from "../../services/LabelService";


let pkg: Package;
let label: Label;
let error: Error;

Given('a package with valid weight {int} height, {int} width, {int} length, and weight {float} kg, and delivery type {string}', function (height, width, length, weight, deliveryType) {
  const deliveryEnum = DeliveryType[deliveryType as keyof typeof DeliveryType];
  if (!deliveryEnum) {
    throw new Error(`Invalid delivery type: ${deliveryType}`);
  }
  pkg = new Package("Id", height, width, length, weight, deliveryEnum);
});


When('the label is created for the valid weight package', function () {
  label = LabelService.createLabel(pkg);
});

Then('the label for valid weight should be created successfully', function () {
  assert.notEqual(label, null, "Label should be created");
});