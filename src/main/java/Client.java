import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(schema = "copy", name = "client")

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idClient;
    @Column(name = "nume")
    private String numeClient;
    @Column(name = "tel")
    private String numarTelefon;
    @Column(name = "CUI")
    private String CUI;
    @Column(name = "oras")
    private String oras;

    public Client() {
    }

    public Client(Long idClient, String numeClient, String numarTelefon, String CUI, String oras) {
        this.idClient = idClient;
        this.numeClient = numeClient;
        this.numarTelefon = numarTelefon;
        this.CUI = CUI;
        this.oras = oras;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public String getCUI() {
        return CUI;
    }

    public void setCUI(String CUI) {
        this.CUI = CUI;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Client.class.getSimpleName() + "[", "]")
                .add("idClient=" + idClient)
                .add("numeClient='" + numeClient + "'")
                .add("numarTelefon='" + numarTelefon + "'")
                .add("CUI='" + CUI + "'")
                .add("oras='" + oras + "'")
                .toString();
    }
}