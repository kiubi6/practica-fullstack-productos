import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router'; // 1. Importamos el Router para la navegación
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
  productosOriginales: Producto[] = [];
  terminoBusqueda: string = '';

  // Objeto para el formulario del Modal
  productoSeleccionado: Producto = this.limpiarProducto();

  constructor(
    private productoService: ProductoService,
    private router: Router // 2. Inyectamos el Router
  ) {}

  ngOnInit(): void {
    this.obtenerProductos();
  }

  // --- 1. CONSULTAR (READ) ---
  obtenerProductos() {
    this.productoService.listar().subscribe({
      next: (data) => {
        this.productosOriginales = data;
        this.productos = data;
      },
      error: (err: any) => console.error('Error al cargar productos:', err)
    });
  }

  // --- 2. MOTOR DE BÚSQUEDA (FRONTEND) ---
  filtrar() {
    const termino = this.terminoBusqueda.trim();
    if (!termino) {
      this.productos = [...this.productosOriginales];
      return;
    }
    // Filtrado por ID exacto como pediste
    this.productos = this.productosOriginales.filter(p =>
      p.id?.toString() === termino
    );
  }

  // --- 3. GESTIÓN DE DATOS (CREATE / UPDATE) ---
  limpiarProducto(): Producto {
    return {
      nombre: '',
      marca: '',
      categoria: '',
      precio: 0,
      existencias: 0,
      activo: true // El backend lo gestionará, pero lo inicializamos aquí
    };
  }

  prepararEditar(producto: Producto) {
    this.productoSeleccionado = { ...producto };
  }

  guardar() {
    this.productoService.guardar(this.productoSeleccionado).subscribe({
      next: () => {
        this.obtenerProductos();
        this.productoSeleccionado = this.limpiarProducto();
        this.terminoBusqueda = '';
        alert('¡Operación exitosa!');
      },
      error: (err: any) => alert('Error al guardar el producto')
    });
  }

  // --- 4. ELIMINAR (DELETE LÓGICO) ---
  eliminar(id?: number) {
    if (!id) return;

    // Mensaje preventivo para evitar borrados accidentales
    if (confirm('¿Estás seguro de que deseas desactivar este registro? Podrás recuperarlo luego.')) {
      this.productoService.eliminar(id).subscribe({
        next: () => {
          this.obtenerProductos(); // Refresca la lista (ya no saldrá el desactivado)
          alert('Producto desactivado correctamente');
        },
        error: (err: any) => alert('No se pudo procesar la eliminación')
      });
    }
  }

  // --- 5. SEGURIDAD: CERRAR SESIÓN ---
  salir() {
    // Removemos la marca de sesión simulada
    localStorage.removeItem('isLoggedIn');
    // Navegación programática hacia el Login
    this.router.navigate(['/login']);
  }
}
