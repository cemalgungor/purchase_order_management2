
export interface IProduct{
  id?:number,
  productName?:string,
  category?:string

}
export class productModel implements IProduct{
  constructor(
    public id?: number,
    public productName?: string,
    public category?: string,
  ) {}
}
