package org.openapitools.openapidiff.core.model.schema;

import static org.openapitools.openapidiff.core.model.BackwardIncompatibleProp.SCHEMA_MAX_PROPERTIES_CHANGED;

import org.openapitools.openapidiff.core.model.Changed;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.DiffResult;

public class ChangedMaxProperties implements Changed {
  private Integer oldValue;
  private Integer newValue;
  private DiffContext context;

  public ChangedMaxProperties(Integer oldValue, Integer newValue, DiffContext context) {
    this.oldValue = oldValue;
    this.newValue = newValue;
    this.context = context;
  }

  public Integer getOldValue() {
    return oldValue;
  }

  public Integer getNewValue() {
    return newValue;
  }

  @Override
  public DiffResult isChanged() {
    if (oldValue == null && newValue == null) {
      return DiffResult.NO_CHANGES;
    }

    if (oldValue == null) {
      if (SCHEMA_MAX_PROPERTIES_CHANGED.enabled(context)) {
        return DiffResult.INCOMPATIBLE;
      } else {
        return DiffResult.COMPATIBLE;
      }
    }

    if (newValue == null) {
      return DiffResult.COMPATIBLE;
    }

    if (!oldValue.equals(newValue)) {
      if (SCHEMA_MAX_PROPERTIES_CHANGED.enabled(context) && oldValue > newValue) {
        return DiffResult.INCOMPATIBLE;
      } else {
        return DiffResult.COMPATIBLE;
      }
    }

    return DiffResult.NO_CHANGES;
  }
}
