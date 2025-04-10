package itson.sistemarestaurantedominio;

import itson.sistemarestaurantedominio.Cliente;
import itson.sistemarestaurantedominio.EstadoComanda;
import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantedominio.ProductoComanda;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-04-09T17:49:35", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Comanda.class)
public class Comanda_ { 

    public static volatile ListAttribute<Comanda, ProductoComanda> productosSolicitados;
    public static volatile SingularAttribute<Comanda, Cliente> cliente;
    public static volatile SingularAttribute<Comanda, EstadoComanda> estado;
    public static volatile SingularAttribute<Comanda, Mesa> mesa;
    public static volatile SingularAttribute<Comanda, String> folio;
    public static volatile SingularAttribute<Comanda, Calendar> fechaHoraCreacion;
    public static volatile SingularAttribute<Comanda, Long> id;

}