import polyglot
import math
import re

class StacCreator:
    def create(self, collection_file_path, theme_publication):
        print("Hello from Python.")

        print(collection_file_path)
        print(theme_publication.getIdentifier())
        print(theme_publication.getItems()[0].getGeometry())

        return "fubar"

polyglot.export_value("StacCreator", StacCreator)
