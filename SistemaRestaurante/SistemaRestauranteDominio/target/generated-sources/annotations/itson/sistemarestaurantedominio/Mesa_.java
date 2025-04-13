package itson.sistemarestaurantedominio;

import itson.sistemarestaurantedominio.Comanda;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-04-12T23:22:23", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Mesa.class)
public class Mesa_ { 

    public static volatile SingularAttribute<Mesa, Integer> numero;
    public static volatile ListAttribute<Mesa, Comanda> comandas;
    public static volatile SingularAttribute<Mesa, Long> id;

}