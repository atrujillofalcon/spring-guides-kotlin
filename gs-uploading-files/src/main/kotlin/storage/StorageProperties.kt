package storage

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("storage")
open class StorageProperties {

    /**
     * Folder location for storing files
     */
    var location = "upload-dir"

}
