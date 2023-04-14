package de.blocknet.api.base;

import java.util.List;
import java.util.UUID;

public interface ICloudBaseManager {

    ICloudBase getNode(UUID uniqueId);

    ICloudBase getNode(String name);

    List<ICloudBase> getNodes();

    boolean existsNode(UUID uniqueId);

    boolean existsNode(String name);

    ICloudBase createNode(ICloudBase node);


}
