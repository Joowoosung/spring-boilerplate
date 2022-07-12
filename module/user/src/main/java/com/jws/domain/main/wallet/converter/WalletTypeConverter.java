package com.jws.domain.main.wallet.converter;

import javax.persistence.AttributeConverter;

public class WalletTypeConverter implements AttributeConverter<WalletType, String> {

  @Override
  public String convertToDatabaseColumn(WalletType attribute) {
    if (attribute != null) return attribute.getCode();
    else return null;
  }

  @Override
  public WalletType convertToEntityAttribute(String dbData) {
    return WalletType.parse(dbData);
  }
}
