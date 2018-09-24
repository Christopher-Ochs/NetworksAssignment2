import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatMessage
{
    private Image m_imageMessage;
    private String m_stringMessage;
    private boolean m_isText;

    public ChatMessage(String stringMessage)
    {
        m_isText = true;
        m_stringMessage = stringMessage;
    }

    public ChatMessage(Image imageMessage)
    {
        m_isText = false;
        m_imageMessage = imageMessage;
    }

    public String getStringMessage()
    {
        return m_stringMessage;
    }

    public Image getImageMessage()
    {
        return m_imageMessage;
    }

    public byte[] getByteArray() throws IOException
    {
        if (m_isText)
        {
            return m_stringMessage.getBytes();
        }
        else
        {
            BufferedImage bImage = SwingFXUtils.fromFXImage(m_imageMessage, null);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos );
            return bos.toByteArray();
        }
    }

    public Boolean isText()
    {
        return m_isText;
    }
}
