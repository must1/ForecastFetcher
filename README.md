# ForecastFetcher

ForecastFetcher is application based on Spring Boot.
It allows to fetch forecast in two options:
1) Current forecast
2) Forecast for days ahead

Paremeters:
- city (mandatory)
- language (optional) - English - en, Russian - ru, Italian - it, Spanish - sp, Ukrainian - ua, German - de, Portuguese - pt, Romanian - ro, Polish - pl, Finnish - fi, Dutch - nl, French - fr, Bulgarian - bg, Swedish - se, Chinese Traditional - zh_tw, Chinese Simplified - zh_cn, Turkish - tr
- units - you can use different types of metric systems by units = metric or imperial
- numbersOfDaysAhead - by this parameter we put how many days ahead do we want to have the weather - default 7 (max 16)

Example queries for:
1) Current forecast - forecast?city=london&units=metric , forecast?city=london
2) Future forecast - future/forecast?city=london&units=metric , future/forecast?numberOfDaysAhead=9&city=london&units=metric
