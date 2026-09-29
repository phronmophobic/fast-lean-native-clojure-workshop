#include "small3dlib/small3dlib.h"

typedef void (*DrawPixelCallback)(S3L_PixelInfo *p);
void setPixelCallback(DrawPixelCallback p);
