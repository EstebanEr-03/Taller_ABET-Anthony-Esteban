public class RolDePagos {
    double aporteSeguro,ImpuestoRenta;// Aporte al seguro e impuesto a la renta
    // Constructor de la clase RolDePagos
    public RolDePagos(double aporteSeguro, double impuestoRenta) {
        this.aporteSeguro = aporteSeguro;
        ImpuestoRenta = impuestoRenta;
    }
    // Método getter para obtener el aporte al seguro
    public double getAporteSeguro() {
        return aporteSeguro;
    }
    // Método setter para establecer el aporte al seguro
    public void setAporteSeguro(double aporteSeguro) {
        this.aporteSeguro = aporteSeguro;
    }
    // Método getter para obtener el impuesto a la renta
    public double getImpuestoRenta() {
        return ImpuestoRenta;
    }
    // Método setter para establecer el impuesto a la renta
    public void setImpuestoRenta(double impuestoRenta) {
        ImpuestoRenta = impuestoRenta;
    }
    // Método toString para representar el objeto como una cadena de texto
    @Override
    public String toString() {
        return "RolDePagos{" +
                "aporteSeguro=" + aporteSeguro +
                ", ImpuestoRenta=" + ImpuestoRenta +
                '}';
    }
}
