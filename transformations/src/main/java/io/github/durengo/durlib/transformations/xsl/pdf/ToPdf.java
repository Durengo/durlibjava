package io.github.durengo.durlib.transformations.xsl.pdf;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

public class ToPdf {

    private String new_filename;
    private String validator_filename;
    private String parse_filename;
    private boolean parse_status;

    public ToPdf(String new_filename, String validator_filename, String parse_filename) {
        this.new_filename = new_filename;
        this.validator_filename = validator_filename;
        this.parse_filename = parse_filename;
    }

    public String getNew_filename() {
        return new_filename;
    }

    public void setNew_filename(String new_filename) {
        this.new_filename = new_filename;
    }

    public String getValidator_filename() {
        return validator_filename;
    }

    public void setValidator_filename(String validator_filename) {
        this.validator_filename = validator_filename;
    }

    public String getParse_filename() {
        return parse_filename;
    }

    public void setParse_filename(String parse_filename) {
        this.parse_filename = parse_filename;
    }

    public boolean isParse_status() {
        return parse_status;
    }

    public void setParse_status(boolean parse_status) {
        this.parse_status = parse_status;
    }

    public void convertToPdf() {
        ClassLoader classLoader = ToPdf.class.getClassLoader();
        URL resourceUrl = null;
        resourceUrl = classLoader.getResource(validator_filename);

        OutputStream out;

        try {
            StreamSource xmlSource = new StreamSource(new File(parse_filename));
            FopFactory fopFactory = FopFactory.newInstance(new File(parse_filename));
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

            out = new FileOutputStream(new_filename);

            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(resourceUrl.openStream()));

            Result res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(xmlSource, res);

            parse_status = true;

            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            parse_status = false;
        }
    }
}
