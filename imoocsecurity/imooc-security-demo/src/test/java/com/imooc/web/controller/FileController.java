package com.imooc.web.controller;

import com.imooc.web.dto.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    private final  String FOLDER = "file";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
      log.info ("name : {}, OriginalFileName : {}, size : {}"
              ,file.getName (),file.getOriginalFilename (),file.getSize ());

        File dir = new File (FOLDER);
        if(!dir.exists ()){
            dir.mkdirs ();
        }
        File fe = new File (FOLDER, new Date ().getTime () + ".txt");
        file.transferTo (fe);
        return new FileInfo (fe.getAbsolutePath ());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest req, HttpServletResponse resp){
        try(InputStream is = new FileInputStream (FOLDER+"/"+id+".txt");
        OutputStream os = resp.getOutputStream ()){
            resp.setContentType ("application/x-download");
            resp.addHeader ("Content-Disposition","attachment;filename = test.txt");
            IOUtils.copy (is,os);
            os.flush ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
