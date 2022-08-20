package org.luff.tp.service;

import org.springframework.stereotype.Service;

@Service
public class DownloadService {

    public String download(Integer paperId) {
        return "http://localhost:8080/tp/papers/" + paperId + "/download.png";
    }
}
