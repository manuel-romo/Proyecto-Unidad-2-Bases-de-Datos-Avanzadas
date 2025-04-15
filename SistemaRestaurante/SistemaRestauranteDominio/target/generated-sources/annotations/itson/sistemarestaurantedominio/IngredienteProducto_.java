package itson.sistemarestaurantedominio;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.Producto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-04-13T01:11:41", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(IngredienteProducto.class)
public class IngredienteProducto_ { 

    public static volatile SingularAttribute<IngredienteProducto, Long> id;
    public static volatile SingularAttribute<IngredienteProducto, Float> cantidad;
    public static volatile SingularAttribute<IngredienteProducto, Producto> producto;
    public static volatile SingularAttribute<IngredienteProducto, Ingrediente> ingrediente;

}