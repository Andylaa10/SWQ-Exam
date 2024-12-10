import {DeliveryType} from "./DeliveryType";

export class Package {
  private id: number;
  private height: number;
  private width: number;
  private length: number;
  private weight: number;
  private deliveryType: DeliveryType;

  constructor(id: number, height: number, width: number, length: number, weight: number, deliveryType: DeliveryType) {
    this.id = id;
    this.height = height;
    this.width = width;
    this.length = length;
    this.weight = weight;
    this.deliveryType = deliveryType;
  }

  // Getters
  public getId(): number {
    return this.id;
  }

  public getHeight(): number {
    return this.height;
  }

  public getWidth(): number {
    return this.width;
  }

  public getLength(): number {
    return this.length;
  }

  public getWeight(): number {
    return this.weight;
  }

  public getDeliveryType(): DeliveryType {
    return this.deliveryType;
  }

  // Setters
  public setId(id: number): void {
    this.id = id;
  }

  public setHeight(height: number): void {
    this.height = height;
  }

  public setWidth(width: number): void {
    this.width = width;
  }

  public setLength(length: number): void {
    this.length = length;
  }

  public setWeight(weight: number): void {
    this.weight = weight;
  }

  public setDeliveryType(deliveryType: DeliveryType): void {
    this.deliveryType = deliveryType;
  }

  public toString(): string {
    return `Package{id=${this.id}, height=${this.height}, width=${this.width}, length=${this.length}, weight=${this.weight}, deliveryType=${this.deliveryType}}`;
  }
}