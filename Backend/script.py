#!/usr/bin/env python3
"""
Generador automático de endpoints, DAOs y services para modelos
"""

import os
from typing import List

# Lista de modelos extraída de tu imagen
MODELOS = [
    "area_servicio",
    "ciudad",
    "cliente",
    "contrato",
    "correo_cliente",
    "correo_persona",
    "cotizacion",
    "dato_metrologico",
    "encargado",
    "equipo",
    "equipo_cliente",
    "fabricante",
    "mantenimiento",
    "marca",
    "modelo",
    "pais",
    "persona",
    "representante_legal",
    "sede",
    "telefono_cliente",
    "telefono_persona",
    "tipo_equipo",
    "verificacion_tecnica",
    "verificacion_tecnica_tipo_equipo"
]

def capitalize_first_letter(text: str) -> str:
    """Capitaliza la primera letra de cada palabra separada por guión bajo"""
    return ''.join(word.capitalize() for word in text.split('_'))



def generate_service_content(modelo: str) -> str:
    """Genera el contenido del archivo Service con conexión por usuario"""

    class_name = capitalize_first_letter(modelo)

    return f"""package com.example.backend.service;

import com.example.backend.DAOs.interfaces.{modelo}DAO;
import com.example.backend.DAOs.implemetaciones.{modelo}DAOImpl;
import com.example.backend.model.conexion.conexionBD;
import com.example.backend.modelDTO.{modelo}DTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class {modelo}Service {{

    public List<{modelo}DTO> getAll(String user, String password) {{
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {{
            {modelo}DAO dao = new {modelo}DAOImpl(conn);
            return dao.findAll();
        }} catch (SQLException e) {{
            throw new RuntimeException("Error al obtener todos los registros", e);
        }}
    }}

    public Optional<{modelo}DTO> getById(String user, String password, String id) {{
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {{
            {modelo}DAO dao = new {modelo}DAOImpl(conn);
            return Optional.ofNullable(dao.findById(id));
        }} catch (SQLException e) {{
            throw new RuntimeException("Error al obtener por ID", e);
        }}
    }}

    public void save(String user, String password, {modelo}DTO entity) {{
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {{
            {modelo}DAO dao = new {modelo}DAOImpl(conn);
            dao.insert(entity);
        }} catch (SQLException e) {{
            throw new RuntimeException("Error al guardar la entidad", e);
        }}
    }}

    public void update(String user, String password, String id, {modelo}DTO entity) {{
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {{
            {modelo}DAO dao = new {modelo}DAOImpl(conn);
            if (dao.findById(id) == null) {{
                throw new RuntimeException("{modelo} con ID " + id + " no encontrado.");
            }}
            // entity.setId(id); // Descomenta si tu modelo tiene setter de ID
            dao.insert(entity);
        }} catch (SQLException e) {{
            throw new RuntimeException("Error al actualizar la entidad", e);
        }}
    }}

    public void deleteById(String user, String password, String id) {{
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {{
            {modelo}DAO dao = new {modelo}DAOImpl(conn);
            if (dao.findById(id) == null) {{
                throw new RuntimeException("{modelo} con ID " + id + " no encontrado.");
            }}
            dao.deleteById(id);
        }} catch (SQLException e) {{
            throw new RuntimeException("Error al eliminar la entidad", e);
        }}
    }}
}}
"""


def generate_controller_content(modelo: str) -> str:
    """Genera el contenido del archivo Controller con user/password"""

    class_name = capitalize_first_letter(modelo)

    return f"""package com.example.backend.controller;

import com.example.backend.modelDTO.{modelo}DTO;
import com.example.backend.service.{modelo}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/{modelo.replace('_', '-')}")
@CrossOrigin(origins = "*")
public class {modelo}Controller {{

    @Autowired
    private {modelo}Service {modelo}Service;

    @GetMapping
    public ResponseEntity<List<{modelo}DTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {{
        List<{modelo}DTO> items = {modelo}Service.getAll(user, password);
        return ResponseEntity.ok(items);
    }}

    @GetMapping("/{{id}}")
    public ResponseEntity<{modelo}DTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {{
        Optional<{modelo}DTO> item = {modelo}Service.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }}

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody {modelo}DTO {modelo}) {{
        {modelo}Service.save(user, password, {modelo});
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }}

    @PutMapping("/{{id}}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody {modelo}DTO {modelo}) {{
        try {{
            {modelo}Service.update(user, password, id, {modelo});
            return ResponseEntity.ok().build();
        }} catch (RuntimeException e) {{
            return ResponseEntity.notFound().build();
        }}
    }}

    @DeleteMapping("/{{id}}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {{
        try {{
            {modelo}Service.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        }} catch (RuntimeException e) {{
            return ResponseEntity.notFound().build();
        }}
    }}
}}
"""


def create_directories():
    """Crea los directorios necesarios"""
    directories = [
        "generated/dao",
        "generated/service", 
        "generated/controller",
        "generated/model"
    ]
    
    for directory in directories:
        os.makedirs(directory, exist_ok=True)
        print(f"✓ Directorio creado: {directory}")

def generate_all_files():
    """Genera todos los archivos para todos los modelos"""
    print("🚀 Iniciando generación de archivos...")
    print(f"📋 Modelos a procesar: {len(MODELOS)}")
    
    create_directories()
    
    for modelo in MODELOS:
        
    
        # Generar Service
        service_content = generate_service_content(modelo)
        service_filename = f"generated/service/{modelo}Service.java"
        with open(service_filename, 'w', encoding='utf-8') as f:
            f.write(service_content)
        
        # Generar Controller
        controller_content = generate_controller_content(modelo)
        controller_filename = f"generated/controller/{modelo}Controller.java"
        with open(controller_filename, 'w', encoding='utf-8') as f:
            f.write(controller_content)
        
        
        print(f"✅ Archivos generados para: {modelo}")

    print(f"\n🎉 ¡Generación completada!")
    print(f"📁 Archivos generados en el directorio 'generated/'")
    print(f"📊 Total de archivos creados: {len(MODELOS) * 4}")

def generate_summary():
    """Genera un resumen de las URLs generadas"""
    print("\n📝 RESUMEN DE ENDPOINTS GENERADOS:")
    print("=" * 50)
    
    for modelo in MODELOS:
        base_url = f"/api/{modelo.replace('_', '-')}"
        print(f"\n🔗 {capitalize_first_letter(modelo)}:")
        print(f"   GET    {base_url}           - Obtener todos")
        print(f"   GET    {base_url}/{{id}}      - Obtener por ID")  
        print(f"   POST   {base_url}           - Crear nuevo")
        print(f"   PUT    {base_url}/{{id}}      - Actualizar")
        print(f"   DELETE {base_url}/{{id}}      - Eliminar")

if __name__ == "__main__":
    print("🏗️  GENERADOR DE ENDPOINTS, DAOS Y SERVICES")
    print("=" * 50)
    
    generate_all_files()
    generate_summary()
    
    print(f"\n📋 PRÓXIMOS PASOS:")
    print("1. Revisa los archivos generados en el directorio 'generated/'")
    print("2. Ajusta los packages según tu estructura de proyecto")
    print("3. Completa los campos específicos en cada modelo")
    print("4. Añade validaciones y lógica de negocio según necesites")
    print("5. Configura las dependencias en tu proyecto Spring Boot")