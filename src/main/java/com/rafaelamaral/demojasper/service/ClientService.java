package com.rafaelamaral.demojasper.service;

import com.rafaelamaral.demojasper.domain.Client;
import com.rafaelamaral.demojasper.repository.ClientRepository;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public byte[] getAllPdf() {
        try {
            var inputStream = this.getClass().getResourceAsStream(
                    "/report/demoj-jasper-client.jasper");

            var allClients = getAll();
            var dataSource = new JRBeanCollectionDataSource(allClients);

            var parametros = new HashMap<String, Object>();
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            try {
                throw new ReportException("Não foi possível emitir relatório de vendas diárias", e);
            } catch (ReportException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
