package br.com.guilhermenogueira.loja.loja.converters.outbound;

import br.com.guilhermenogueira.loja.loja.models.Campaign;
import br.com.guilhermenogueira.loja.loja.models.ImageCloud;
import br.com.guilhermenogueira.loja.loja.presenters.CampaignPresenter;
import br.com.guilhermenogueira.loja.loja.presenters.ImageCloudPresenter;
import com.sun.istack.NotNull;

public class CampaignConverterOutbound {
    public static CampaignPresenter converter(@NotNull Campaign campaign) {
        return new CampaignPresenter(campaign, new ImageCloudPresenter(new ImageCloud("",campaign.getImageUrl(),"")));
    }
}
