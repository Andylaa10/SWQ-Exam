import {Given, Then, When} from "@cucumber/cucumber";
import {DeliveryType} from "../../models/DeliveryType";
import {Package} from "../../models/Package";
import {LabelService} from "../../services/LabelService";
import assert from "assert";
import {Label} from "../../models/Label";


let pkg: Package;
let label: Label;

Given('a package with valid dimensions {int} height, {int} width, {int} length, and weight {float} kg, and delivery type {string}', function (height, width, length, weight, deliveryType) {
  const deliveryEnum = DeliveryType[deliveryType as keyof typeof DeliveryType];
  if (!deliveryEnum) {
    throw new Error(`Invalid delivery type: ${deliveryType}`);
  }
  pkg = new Package("Id", height, width, length, weight, deliveryEnum);
});


When('the label is created for the valid dimensions package', function () {
  label = LabelService.createLabel(pkg);
});

Then('the label for valid dimensions should be created successfully', function () {
  assert.notEqual(label, null, "Label should be created");
});