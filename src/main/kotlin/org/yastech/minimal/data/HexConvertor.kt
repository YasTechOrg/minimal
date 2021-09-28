package org.yastech.minimal.data

import org.apache.commons.codec.DecoderException
import org.apache.commons.codec.binary.Hex
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

@Service
class HexConvertor
{
    fun encode(str: String): String
    {
        return String(Hex.encodeHex(str.toByteArray(StandardCharsets.UTF_8)))
    }

    fun decode(hex: String?): String
    {
        return try
        {
            String(Hex.decodeHex(hex), StandardCharsets.UTF_8)
        }
        catch (e: DecoderException)
        {
            "Invalid Hex format!"
        }
    }
}