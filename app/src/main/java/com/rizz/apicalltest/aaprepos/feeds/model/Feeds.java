package com.rizz.apicalltest.aaprepos.feeds.model;

public class Feeds {
    String pageImage;
    String pageThumbnail;

    public Feeds(String pageImage, String pageThumbnail) {
        this.pageImage = pageImage;
        this.pageThumbnail = pageThumbnail;
    }

    public String getPageImage() {
        return pageImage;
    }

    public void setPageImage(String pageImage) {
        this.pageImage = pageImage;
    }

    public String getPageThumbnail() {
        return pageThumbnail;
    }

    public void setPageThumbnail(String pageThumbnail) {
        this.pageThumbnail = pageThumbnail;
    }
}
