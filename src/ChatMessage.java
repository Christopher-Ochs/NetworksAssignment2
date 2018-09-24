/*
    Name: Phillip Tallo and Christopher Ochs
    Date: September 18, 2018
    Desc: Assignment 2 Computer Networks.  Client Server Protocol.
 */
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

public class ChatMessage
{
    // Holds the message's image
    private Image m_imageMessage;
    // Holds the message's text
    private String m_stringMessage;
    // Holds the raw byte array
    private byte[] m_byteArray;
    // Used to determine message will hold text or an image
    private boolean m_isText;

    // Constructor with byte array will create proper message object
    public ChatMessage(byte[] byteArray, boolean isText)
    {
        m_isText = isText;
        m_byteArray = byteArray;
        if (isText)
        {
            m_isText = true;
            m_stringMessage = new String(byteArray);
        }
        else
        {
            m_isText = false;
            ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
            m_imageMessage = new Image(in);
        }

    }

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

    // Returns the text message
    public String getStringMessage()
    {
        return m_stringMessage;
    }

    // Returns the image message
    public Image getImageMessage()
    {
        return m_imageMessage;
    }

    // Returns raw byte array
    public byte[] getByteArray() throws IOException
    {
        // If byte array was used to create chatMessage return raw byte array
        if (m_byteArray != null)
        {
            return m_byteArray;
        }
        // If it is a text message convert to raw bytes
        else if (m_isText)
        {
            return m_stringMessage.getBytes();
        }
        // If it is an image convert to bytes using a stream
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
