package itson.sistemarestaurantedominio;

import itson.sistemarestaurantedominio.Comanda;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(Mesa.class)
public class Mesa_ { 

    public static volatile SingularAttribute<Mesa, Integer> numero;
    public static volatile ListAttribute<Mesa, Comanda> comandas;
    public static volatile SingularAttribute<Mesa, Long> id;

}