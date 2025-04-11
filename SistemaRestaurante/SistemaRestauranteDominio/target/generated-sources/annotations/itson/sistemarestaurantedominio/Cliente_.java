package itson.sistemarestaurantedominio;

import itson.sistemarestaurantedominio.Comanda;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-04-10T19:58:40", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> apellidoPaterno;
    public static volatile SingularAttribute<Cliente, Calendar> fechaRegistro;
    public static volatile ListAttribute<Cliente, Comanda> comandas;
    public static volatile SingularAttribute<Cliente, Long> id;
    public static volatile SingularAttribute<Cliente, String> telefono;
    public static volatile SingularAttribute<Cliente, String> correoElectronico;
    public static volatile SingularAttribute<Cliente, String> nombres;
    public static volatile SingularAttribute<Cliente, String> apellidoMaterno;

}