# ARCHITECTURE.md

## 1. Общая структура

Проект построен на Spring Boot и использует **Hexagonal Architecture (Ports & Adapters)**.

Основные слои:

- `domain` — бизнес-ядро
- `application` — use cases / прикладная логика
- `infrastructure` — адаптеры (web, persistence)
- `config` — конфигурация Spring

---

## 2. Структура пакетов

### 2.1 domain

`com.touroperator.domain`

Бизнес-ядро системы (чистая Java логика).

Содержит:

- `model` — доменные модели (сущности, агрегаты, value objects)
- `exception` — доменные исключения
- `port.in` — входящие порты (use cases)
- `port.out` — исходящие порты (внешние зависимости)

Правила:
- не зависит от Spring / JPA / HTTP
- не содержит DTO
- не содержит инфраструктурного кода

---

### 2.2 application

`com.touroperator.application`

Слой прикладной логики (use case orchestration).

Содержит:

- `service` — реализации входящих портов (`port.in`)
- `dto`:
    - `request`
    - `response`
    - `command`

Правила:
- зависит только от `domain`
- содержит оркестрацию бизнес-процессов
- не содержит SQL / JPA / REST логики

---

### 2.3 infrastructure

`com.touroperator.infrastructure`

Слой внешних адаптеров.

**`adapter/in/web`**
- REST controllers
- exception handlers (advice)

**`adapter/out/persistence`**
- `entity` — JPA модели
- Spring Data repositories
- mappers (domain ↔ entity)

Правила:
- зависит от `application` и `domain`
- не содержит бизнес-логики
- отвечает за взаимодействие с внешними системами

---

### 2.4 config

`com.touroperator.config`

Spring конфигурация:

- bean definitions
- технические настройки приложения

---

## 3. Зависимости между слоями

### Разрешённые

| От | До |
|----|----|
| `application` | `domain` |
| `infrastructure` | `application` |
| `infrastructure` | `domain` |
| `config` | `application` / `infrastructure` |

### Запрещённые

| От | До |
|----|----|
| `domain` | `application` |
| `domain` | `infrastructure` |
| `application` | `infrastructure` (напрямую) |
| `domain` | Spring / JPA / Web зависимости |

---

## 4. Принцип архитектуры (Hexagonal Core)

Архитектура направлена внутрь:

```
┌──────────────────────────┐
│     infrastructure       │
│ (web / persistence)      │
└───────────┬──────────────┘
            │
┌───────────▼──────────────┐
│       application        │
│   (use cases / services) │
└───────────┬──────────────┘
            │
┌───────────▼──────────────┐
│          domain          │
│   (business core)        │
└──────────────────────────┘
```

`domain` является полностью независимым ядром системы.

## 5. Бизнес-правила бронирования

### 5.1 Правило пересечения интервалов

При проверке доступности тура используется модель **полуоткрытого интервала**:

```
[start_date, end_date)
```

Это означает:

* `start_date` входит в интервал бронирования;
* `end_date` не входит в интервал бронирования.

Следовательно:

* день выезда считается свободным;
* заезд нового туриста в день выезда предыдущего **разрешён**;
* такие интервалы **не считаются пересекающимися**.

Проверка пересечения выполняется по следующему условию:

```sql
start_date < :requestedEnd
AND end_date > :requestedStart
```

Данное правило является архитектурным решением проекта и должно использоваться во всех механизмах проверки доступности туров и создания бронирований.


@Version используется для оптимистичной блокировки на уровне JPA.

Ограничение:
- не предотвращает бизнес-конфликты (пересечение дат)
- не заменяет проверку доменных правил
- используется только для защиты от конкурентных UPDATE одной сущности

Для предотвращения пересечения бронирований используется бизнес-валидация и  уникальные ограничения БД.