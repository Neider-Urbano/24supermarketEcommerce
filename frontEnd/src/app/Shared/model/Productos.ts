export interface Productos{
    productoid: number;
    nombre: string;
    precio: number;
    cantidad: number;
    descripcion: string;
    imgurl: string;
    cedula: number,
    categorias?:[];
}