package itson.sistemarestaurantedominio;

import itson.sistemarestaurantedominio.Comanda;
import itson.sistemarestaurantedominio.Producto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-04-10T21:19:37", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(ProductoComanda.class)
public class ProductoComanda_ { 

    public static volatile SingularAttribute<ProductoComanda, Float> precioUnitario;
    public static volatile ListAttribute<ProductoComanda, String> notas;
    public static volatile SingularAttribute<ProductoComanda, Long> id;
    public static volatile SingularAttribute<ProductoComanda, Integer> cantidad;
    public static volatile SingularAttribute<ProductoComanda, Producto> producto;
    public static volatile SingularAttribute<ProductoComanda, Comanda> comanda;

}