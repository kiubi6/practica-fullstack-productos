import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductoService } from '../../services/producto';
import { Producto } from '../../models/producto';

@Component({
  selector: 'app-producto-lista',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './producto-lista.html',
  styleUrl: './producto-lista.css'
})
export class ProductoListaComponent implements OnInit {

  // Listas para el manejo de datos y filtros
  productos: Producto[] = [];
  productosOriginales: Producto[] = []; // Respaldo para la búsqueda
  terminoBusqueda: string = '';

  // Objeto para el formulario del Modal
  productoSeleccionado: Producto = this.limpiarProducto();

  constructor(private productoService: ProductoService) {}

  ngOnInit(): void {
    this.obtenerProductos();
  }

  // --- 1. CONSULTAR (READ) ---
  obtenerProductos() {
    this.productoService.listar().subscribe({
      next: (data) => {
        this.productosOriginales = data;
        this.productos = data; // Al cargar, mostramos todo
      },
      error: (err: any) => console.error('Error al cargar productos:', err)
    });
  }

  // --- 2. MOTOR DE BÚSQUEDA (FRONTEND) ---
 filtrar() {
   const termino = this.terminoBusqueda.trim();

   // Si el buscador está vacío, regresamos todos los productos
   if (!termino) {
     this.productos = [...this.productosOriginales];
     return;
   }

   // FILTRADO ESTRICTO: Solo comparamos el ID
   this.productos = this.productosOriginales.filter(p => {
     // Usamos toString() para comparar el número del ID con el texto del input
     // Usamos === para que sea una coincidencia EXACTA
     return p.id?.toString() === termino;
   });
 }
  // --- 3. GESTIÓN DE DATOS (CREATE / UPDATE) ---
  limpiarProducto(): Producto {
    return {
      nombre: '',
      marca: '',
      categoria: '',
      precio: 0,
      existencias: 0,
      activo: true
    };
  }

  prepararEditar(producto: Producto) {
    // Clonamos para evitar que la tabla cambie mientras editamos en el modal
    this.productoSeleccionado = { ...producto };
  }

  guardar() {
    this.productoService.guardar(this.productoSeleccionado).subscribe({
      next: () => {
        this.obtenerProductos(); // Refrescamos la lista de la BD
        this.productoSeleccionado = this.limpiarProducto();
        this.terminoBusqueda = ''; // Limpiamos búsqueda tras guardar
        alert('¡Operación exitosa!');
      },
      error: (err: any) => alert('Error al guardar el producto')
    });
  }

  // --- 4. ELIMINAR (DELETE) ---
  eliminar(id?: number) {
    if (!id) return;

    if (confirm('¿Estás seguro de que deseas eliminar este registro de la base de datos?')) {
      this.productoService.eliminar(id).subscribe({
        next: () => {
          this.obtenerProductos();
          alert('Producto eliminado correctamente');
        },
        error: (err: any) => alert('No se pudo eliminar el producto')
      });
    }
  }
}
