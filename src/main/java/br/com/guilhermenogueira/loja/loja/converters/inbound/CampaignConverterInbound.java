package br.com.guilhermenogueira.loja.loja.converters.inbound;

import br.com.guilhermenogueira.loja.loja.models.Campaign;
import br.com.guilhermenogueira.loja.loja.parameters.CampaignRequest;

public class CampaignConverterInbound {
    public static Campaign converter(CampaignRequest request) {
        Campaign campaign = new Campaign();
        campaign.setCode(request.getCode());
        campaign.setDescription(request.getDescription());
        campaign.setActive(true);
        campaign.setImageUrl(request.getImage());
        campaign.setPriceCut(request.getPriceCut());
        return campaign;
    }
}
