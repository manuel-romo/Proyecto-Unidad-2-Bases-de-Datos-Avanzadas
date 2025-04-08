package itson.sistemarestaurantedominio;

import itson.sistemarestaurantedominio.IngredienteProducto;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-04-08T07:51:21", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Ingrediente.class)
public class Ingrediente_ { 

    public static volatile SingularAttribute<Ingrediente, UnidadIngrediente> unidad;
    public static volatile SingularAttribute<Ingrediente, String> direccionImagen;
    public static volatile SingularAttribute<Ingrediente, Long> id;
    public static volatile SingularAttribute<Ingrediente, Float> cantidad;
    public static volatile SingularAttribute<Ingrediente, String> nombre;
    public static volatile ListAttribute<Ingrediente, IngredienteProducto> productos;

}