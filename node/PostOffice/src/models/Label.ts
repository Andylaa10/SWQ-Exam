import {Package} from "./Package";

export class Label{
  private id: string;
  private package: Package;
  private totalCost: number;

  constructor(id: string, pkg: Package, totalCost: number) {
    this.id = id;
    this.package = pkg;
    this.totalCost = totalCost;
  }

  // Getters
  public getId(): string {
    return this.id;
  }

  public getPackage(): Package {
    return this.package;
  }

  public getTotalCost(): number {
    return this.totalCost;
  }

  // Setters
  public setId(id: string): void {
    this.id = id;
  }

  public setPackage(pkg: Package): void {
    this.package = pkg;
  }

  public setTotalCost(totalCost: number): void {
    this.totalCost = totalCost;
  }
}