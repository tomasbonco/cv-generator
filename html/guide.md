# Ako naplniť formulár

## Základné elementy

Základné elementy, sú tie, ktoré sa neopakujú (Meno, Priezvisko, Ulica). Každý z nich má atribút `ng-init`, do neho stačí vpísať hodnotu. Príklad pre meno.

**Prázdny**:

```html
	<input type="text" name="name" ng-model="name" ng-required="true" placeholder="e.g. John." ng-init="name = ''"> 
```

**Naplnený**:

```html
	<input type="text" name="name" ng-model="name" ng-required="true" placeholder="e.g. John." ng-init="name = 'Peter'"> 
```

## Telefón a Email

Prvý telefón a prvý email sú povinné, tie zvyšn= už nie, preto sú v kóde rozdelené do povinnej a voliteľnej časti. Vypĺňanie je jednoduché (príklad pre Phone), hneď pod nadpisom Contact Information nájdi:

```html
<div ng-init="phones = []; _phone = []" class="col-xs-6">
```

Sú tam dve polia, `phones` je počítadlo a obsahuje iba hodnoty `[]`, `_phone` sú jednotlivé telefónne čísla. **POZOR: Počítadlo ráta iba nepovinné telefónne čísla**. To znamená, že prvé telefónne číslo v `_phones` sa namapuje na do povinného telefónneho čísla, a voliteľné telefónne čísla sa vytvoria podľa počtu `[]` v `phones`. Ak človek zadal 3 telefóone čísla, zobrazíš mu ich pomocou: `phones = [ [], [] ]; _phone = [ 'tf1', 'tf2', 'tf3' ]`. Ak chceš zobraziť na konci ešte formulár pre zadanie nového čísla, pridáš ešte jeden `[]` do `phones`. Viď príklad v `filled.html`:

```html
<div ng-init="phones = [ [], [] ]; _phone = [ '0988 02 19', '+420 7350 00 00' ]" class="col-xs-6">
```


## Škola, Zamestnanie, Jazyky, Certifikáty, Schopnosti a Hobby

Fungujú podobne ako telefon ale s tým rozdielom, že nemajú povinnú časť. Z toho vyplýva, že počítadlo sa vzťahuje na reálny počet riadkov (a nie o jeden menej, než tomu bolo vyššie). Defaultne tam už je hodnota `[ [] ]`, aby sa užívateľovi zobrazil prvý - prázdny riadok (pri vytváraní dokumentu). Languages a Skills majú ešte prednastavenú hodnotu na `level`, na `basic`. To je hodnota, ktorá je priradená poslednému `[]` prvku. V prípade vytvárania dokumentu (príklad pre Language) vyzerá riadok takto:

```html
<div ng-init="languages = [ [] ]; _l_language = []; _l_level = ['basic']" class="row">
```

Čo dáva zmysel, pretože, chceme zobraziť jeden riadok (`[ [] ]`), nechceme aby tam bolo nič napísané, ale chceme aby `level` bol automaticky nastavený na basic. Príklad vyplnených Languages, s prázdnym novým riadkom z `filled.html`:
```html
<div ng-init="languages = [ [], [], [] ]; _l_language = [ 'English', 'Spanish' ]; _l_level = [ 'advanced', 'native speaker', 'basic']" class="row">
```

## Heslo

Sa nepredvypĺňa.