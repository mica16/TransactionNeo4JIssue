package utils

import annotation.meta.field

object SpringDataShortAnnotations {
  type Indexed = org.springframework.data.neo4j.annotation.Indexed @field
  type GraphProperty = org.springframework.data.neo4j.annotation.GraphProperty @field
}
