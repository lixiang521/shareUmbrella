package com.pro.umbrella.common.util;

import static com.pro.umbrella.api.pojo.Money.DEF_MC;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.pro.umbrella.api.pojo.Currency;
import com.pro.umbrella.api.pojo.Money;
import com.pro.umbrella.common.CommonConstants;

/**
 * {@link Money} 工具类
 *
 * @author Daniel Li
 * @since 19 July 2017
 */
public class MoneyUtils {

	public static final Currency DEFAULT_CURRENCY = Currency.CNY;

	public static Money of(BigDecimal amount) {
		return of(amount, DEFAULT_CURRENCY);
	}

	public static Money of(BigDecimal amount, Currency currency) {
		return new Money(amount, currency);
	}

	public static Money of(String amount) {
		return of(amount, DEFAULT_CURRENCY);
	}

	public static Money of(String amount, Currency currency) {
		return new Money(createBigDecimalWithDefMC(amount), currency);
	}

	public static Money of(long amount) {
		return of(amount, DEFAULT_CURRENCY);
	}

	public static Money of(long amount, Currency currency) {
		return new Money(BigDecimal.valueOf(amount), currency);
	}

	public static Money of(long amount, int scale) {
		return of(amount, scale, DEFAULT_CURRENCY);
	}

	public static Money of(long amount, int scale, Currency currency) {
		return new Money(BigDecimal.valueOf(amount, scale), currency);
	}

	private static BigDecimal createBigDecimalWithDefMC(String amount) {
		return new BigDecimal(amount, DEF_MC);
	}

	public static Money zero(Currency currency) {
		return new Money(BigDecimal.ZERO, currency);
	}

	public static Money total(Money... monies) {
		if (monies.length == 0) {
			throw new IllegalArgumentException("Money array must not be empty");
		}
		Money total = null;
		for (int i = 0; i < monies.length; i++) {
			if (monies[i] != null) {
				if (total == null) {
					total = Money.zero(monies[i].getCurrency());
				}
				total = total.plus(monies[i]);
			}
		}
		return total;
	}

	public static Money exchange(Money sourceMoney, Map<String, BigDecimal> rates, Currency targetCurrency) {
		if (sourceMoney == null) {
			return null;
		}
		Map<Currency, Map<Currency, BigDecimal>> rateMapping = Maps.newHashMapWithExpectedSize(rates.size());
		for (Map.Entry<String, BigDecimal> rate : rates.entrySet()) {
			List<String> currencies = CommonConstants.SPLITTER_UNDERLINE.splitToList(rate.getKey());
			if (currencies.size() != 2) {
				throw new IllegalArgumentException("rates's key format must match sourceCurrency_targetCurrency. " + rate.getKey());
			}
			Currency source = Currency.valueOf(currencies.get(0).toUpperCase());
			Currency target = Currency.valueOf(currencies.get(1).toUpperCase());
			rateMapping.computeIfAbsent(source, currency -> Maps.newHashMap()).put(target, rate.getValue());
		}
		return sourceMoney.exchange(rateMapping, targetCurrency);
	}

	public static Money exchangeWithSourceScale(Money sourceMoney, Map<String, BigDecimal> rates, Currency targetCurrency) {
		return exchangeWithSourceScale(sourceMoney, rates, targetCurrency, RoundingMode.HALF_EVEN);
	}

	public static Money exchangeWithSourceScale(Money sourceMoney, Map<String, BigDecimal> rates, Currency targetCurrency, RoundingMode mode) {
		Money money = exchange(sourceMoney, rates, targetCurrency);
		if (money != null) {
			return asFixed(money, sourceMoney.getAmount().scale(), mode);
		}
		return null;
	}

	public static Money exchangeWithFixedScale(Money sourceMoney, Map<String, BigDecimal> rates, Currency targetCurrency, int scale) {
		return exchangeWithFixedScale(sourceMoney, rates, targetCurrency, scale, RoundingMode.HALF_EVEN);
	}

	public static Money exchangeWithFixedScale(Money sourceMoney, Map<String, BigDecimal> rates, Currency targetCurrency, int scale, RoundingMode mode) {
		Money money = exchange(sourceMoney, rates, targetCurrency);
		if (money != null) {
			return asFixed(money, scale, mode);
		}
		return null;
	}

	public static Money asFixed(Money source, int scale) {
		return asFixed(source, scale, RoundingMode.HALF_EVEN);
	}

	public static Money asFixed(Money source, int scale, RoundingMode mode) {
		if (source == null) {
			return null;
		}
		BigDecimal amount = BigDecimal.valueOf(source.getAmount().movePointRight(scale).setScale(0, mode).longValue(), scale);
		return Money.of(amount, source.getCurrency());
	}

	public static Money stripTrailingZeros(Money source) {
		if (source == null) {
			return null;
		}
		BigDecimal amount = source.stripTrailingZeros();
		return Money.of(amount, source.getCurrency());
	}
}
