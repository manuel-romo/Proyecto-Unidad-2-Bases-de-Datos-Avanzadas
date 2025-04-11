package itson.sistemarestaurantedominio;

import itson.sistemarestaurantedominio.IngredienteProducto;
import itson.sistemarestaurantedominio.TipoProducto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-04-10T21:19:37", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, Float> precio;
    public static volatile SingularAttribute<Producto, TipoProducto> tipo;
    public static volatile ListAttribute<Producto, IngredienteProducto> ingredientes;
    public static volatile SingularAttribute<Producto, String> direccionImagen;
    public static volatile SingularAttribute<Producto, Long> id;
    public static volatile SingularAttribute<Producto, Boolean> habilitado;
    public static volatile SingularAttribute<Producto, String> nombre;

}