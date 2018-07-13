package eu.tib;

import org.jsoup.Connection;

/*
@author  Qazi Asim Ijaz Ahmad
@E-mail asim.ahmad@tib.eu
*/

class UrlResponse{

    private  Connection.Response response;
    private boolean urlIsAccessible;

    public UrlResponse(){
        setUrlIsAccessible(true);
    }

    public Connection.Response getResponse() {
        return response;
    }

    public void setResponse(Connection.Response response) {
        this.response = response;
    }

    public boolean urlIsAccessible() {
        return urlIsAccessible;
    }

    public void setUrlIsAccessible(boolean urlIsAccessible) {
        this.urlIsAccessible = urlIsAccessible;
    }

}