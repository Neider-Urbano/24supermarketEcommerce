export interface Factura{
    facturaid: number,
    personaid: number,
    productoid: number,
    direccion: string,
    cantidad: number, 
    precio: number,
    formapago:string,
    numerocuenta:number,
    fechafactura: Date,
}