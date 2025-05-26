package io.github.trident.common.utils;

import io.github.trident.common.exception.TridentException;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @projectName: trident
 * @package: io.github.trident.common.utils
 * @className: DigestUtils
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/4/29 14:54
 * @version: 1.0
 */
public class DigestUtils {
    public static String sha512Hex(final String data) {
        return Optional.ofNullable(data).filter(StringUtils::isNoneEmpty).map(item -> {
            try {
                return org.apache.commons.codec.digest.DigestUtils.sha512Hex(data);
            } catch (Exception ex) {
                throw new TridentException(ex);
            }
        }).orElse(null);
    }
}
