package itson.sistemarestaurantedominio;

import itson.sistemarestaurantedominio.IngredienteProducto;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(Ingrediente.class)
public class Ingrediente_ { 

    public static volatile SingularAttribute<Ingrediente, UnidadIngrediente> unidad;
    public static volatile SingularAttribute<Ingrediente, String> direccionImagen;
    public static volatile SingularAttribute<Ingrediente, Long> id;
    public static volatile SingularAttribute<Ingrediente, Float> cantidad;
    public static volatile SingularAttribute<Ingrediente, Boolean> habilitado;
    public static volatile SingularAttribute<Ingrediente, String> nombre;
    public static volatile ListAttribute<Ingrediente, IngredienteProducto> productos;

}