package com.epilabs.epiguard.ui.components.examples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes

@Composable
fun InstagramProfileEdit(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
            .background(color = AppColors.color_white)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 88.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = AppColors.color_pale_grey)
            )
            Text(
                text = "Cancel",
                color = AppColors.color_900,
                lineHeight = 1.31.em,
                style = AppTypes.type_Body_Large_400.copy(letterSpacing = (-0.33).sp),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 0.dp, y = 22.5.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Edit Profile",
                color = AppColors.color_900,
                textAlign = TextAlign.Center,
                lineHeight = 1.31.em,
                style = AppTypes.type_Body_Large_400.copy(letterSpacing = (-0.33).sp),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .fillMaxWidth()
            )
            Text(
                text = "Done",
                color = AppColors.color_Primary_500,
                textAlign = TextAlign.End,
                lineHeight = 1.31.em,
                style = AppTypes.type_Body_Large_400.copy(letterSpacing = (-0.33).sp),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 0.dp, y = 22.5.dp)
                    .fillMaxWidth()
            )
        }
        Box(
            modifier = Modifier
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 44.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 21.dp, end = 300.dp, top = 14.dp, bottom = 12.dp)
            ) {
                Text(
                    text = "9:41",
                    color = AppColors.color_black,
                    textAlign = TextAlign.Center,
                    style = AppTypes.type_Typography_Label_Large.copy(letterSpacing = (-0.3).sp),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .offset(x = 0.dp, y = 0.dp)
                        .fillMaxWidth()
                )
            }
            Image(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = "Wifi",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 315.69.dp, y = 17.33.dp)
                    .requiredWidth(width = 15.dp)
                    .requiredHeight(height = 11.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = "Mobile Signal",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 293.67.dp, y = 17.67.dp)
                    .requiredWidth(width = 17.dp)
                    .requiredHeight(height = 11.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = "Battery",
                tint = AppColors.color_grey,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 336.dp, y = 18.dp)
                    .requiredWidth(width = 25.dp)
                    .requiredHeight(height = 11.dp)
            )
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 88.dp)
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 161.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = AppColors.color_white)
            )
            Image(
                painter = painterResource(id = R.drawable.guy_3),
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .fillMaxSize()
                    .border(border = BorderStroke(0.5.dp, AppColors.color_black.copy(alpha = 0.1f)))
            )
            Text(
                text = "Change Profile Photo" ,
                color = AppColors.color_Primary_500,
                textAlign = TextAlign.Center,
                lineHeight = 1.62.em,
                style = AppTypes.type_Body_small_400.copy(letterSpacing = (-0.05).sp),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 0.dp, y = 56.25.dp)
                    .fillMaxWidth()
            )

        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 248.dp)
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 208.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 375.dp)
                    .requiredHeight(height = 208.dp)
                    .background(color = AppColors.color_white)
            )
            Box(
                modifier = Modifier
                    .requiredWidth(width = 375.dp)
                    .requiredHeight(height = 48.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = AppColors.color_white)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 96.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = AppColors.color_white)
                    )
                    Text(
                        text = "Jacob West",
                        color = AppColors.color_900,
                        style = AppTypes.type_Body_Large_400.copy(letterSpacing = (-0.33).sp),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .offset(x = 0.dp, y = (-0.5).dp)
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = "Name",
                    color = AppColors.color_900,
                    style = AppTypes.type_Typography_Label_Large.copy(letterSpacing = (-0.25).sp),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .offset(x = 0.dp, y = 0.dp)
                        .fillMaxWidth()
                )
            }
            Image(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = "Separator",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 112.dp, y = 47.5.dp)
                    .requiredWidth(width = 247.dp)
                    .requiredHeight(height = 1.dp)
                    .border(border = BorderStroke(0.33000001311302185.dp, AppColors.color_Gray_800.copy(alpha = 0.29f)))
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 48.dp)
                    .requiredWidth(width = 375.dp)
                    .requiredHeight(height = 48.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = AppColors.color_white)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 96.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = AppColors.color_white)
                    )
                    Text(
                        text = "jacob_w",
                        color = AppColors.color_900,
                        style = AppTypes.type_Body_Large_400.copy(letterSpacing = (-0.33).sp),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .offset(x = 0.dp, y = (-0.5).dp)
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = "Username",
                    color = AppColors.color_900,
                    style = AppTypes.type_Typography_Label_Large.copy(letterSpacing = (-0.25).sp),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .offset(x = 0.dp, y = 0.dp)
                        .fillMaxWidth()
                )
            }
            Image(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = "Separator",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 112.dp, y = 95.5.dp)
                    .requiredWidth(width = 247.dp)
                    .requiredHeight(height = 1.dp)
                    .border(border = BorderStroke(0.33000001311302185.dp, AppColors.color_Gray_800.copy(alpha = 0.29f)))
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 96.dp)
                    .requiredWidth(width = 375.dp)
                    .requiredHeight(height = 48.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = AppColors.color_white)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 96.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = AppColors.color_white)
                    )
                    Text(
                        text = "Website",
                        color = AppColors.color_Gray_800.copy(alpha = 0.3f),
                        style = AppTypes.type_Body_Large_400.copy(letterSpacing = (-0.33).sp),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .offset(x = 0.dp, y = (-0.5).dp)
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = "Website",
                    color = AppColors.color_900,
                    style = AppTypes.type_Typography_Label_Large.copy(letterSpacing = (-0.25).sp),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .offset(x = 0.dp, y = 0.dp)
                        .fillMaxWidth()
                )
            }
            Image(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = "Separator",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 112.dp, y = 143.5.dp)
                    .requiredWidth(width = 247.dp)
                    .requiredHeight(height = 1.dp)
                    .border(border = BorderStroke(0.33000001311302185.dp, AppColors.color_Gray_800.copy(alpha = 0.29f)))
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 144.dp)
                    .requiredWidth(width = 375.dp)
                    .requiredHeight(height = 64.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = AppColors.color_white)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 96.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = AppColors.color_white)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                color = AppColors.color_900,
                                fontSize = AppTypes.type_Typography_Label_Large.fontSize
                            )) { append("Digital goodies designer ") }
                            withStyle(style = SpanStyle(
                                color = AppColors.darkblue,
                                fontSize = AppTypes.type_Typography_Label_Large.fontSize
                            )) { append("@pixsellz") }
                            withStyle(style = SpanStyle(
                                color = AppColors.color_900,
                                fontSize = AppTypes.type_Typography_Label_Large.fontSize
                            )) { append(" \nEverything is designed.") }
                        },
                        style = AppTypes.type_Typography_Label_Large,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .offset(x = 0.dp, y = (-1).dp)
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = "Bio",
                    color = AppColors.color_900,
                    style = AppTypes.type_Typography_Label_Large.copy(letterSpacing = (-0.25).sp),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .offset(x = 0.dp, y = (-9).dp)
                        .fillMaxWidth()
                )
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 456.dp)
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 49.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = AppColors.color_white)
            )
            Text(
                text = "Switch to Professional Account",
                color = AppColors.color_Primary_500,
                style = AppTypes.type_Typography_Label_Large.copy(letterSpacing = (-0.25).sp),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 0.dp, y = 0.5.dp)
                    .fillMaxWidth()
            )
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 505.dp)
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 193.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = AppColors.color_white)
            )
            Text(
                text = "Private Information",
                color = AppColors.color_900,
                lineHeight = 1.33.em,
                style = AppTypes.type_Typography_Label_Large.copy(letterSpacing = (-0.25).sp),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 0.dp, y = (-72.25).dp)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 48.dp) // Adjusted to stack within 193.dp height
                    .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = AppColors.color_white)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 96.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = AppColors.color_white)
                    )
                    Text(
                        text = "jacob.west@gmail.com",
                        color = AppColors.color_900,
                        style = AppTypes.type_Body_Large_400.copy(letterSpacing = (-0.33).sp),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .offset(x = 0.dp, y = (-0.5).dp)
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = "Email",
                    color = AppColors.color_900,
                    style = AppTypes.type_Typography_Label_Large.copy(letterSpacing = (-0.25).sp),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .offset(x = 0.dp, y = 0.dp)
                        .fillMaxWidth()
                )
            }
            Image(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = "Separator",
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 96.dp) // Position separator below email
                    .border(border = BorderStroke(0.33000001311302185.dp, AppColors.color_Gray_800.copy(alpha = 0.29f)))
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 96.dp) // Adjusted to stack below email
                    .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = AppColors.color_white)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 96.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = AppColors.color_white)
                    )
                    Text(
                        text = "+1 202 555 0147",
                        color = AppColors.color_900,
                        style = AppTypes.type_Body_Large_400.copy(letterSpacing = (-0.33).sp),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .offset(x = 0.dp, y = (-0.5).dp)
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = "Phone",
                    color = AppColors.color_900,
                    style = AppTypes.type_Typography_Label_Large.copy(letterSpacing = (-0.25).sp),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .offset(x = 0.dp, y = 0.dp)
                        .fillMaxWidth()
                )
            }
            Image(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = "Separator",
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 144.dp) // Position separator below phone
                    .border(border = BorderStroke(0.33000001311302185.dp, AppColors.color_Gray_800.copy(alpha = 0.29f)))
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 144.dp) // Adjusted to stack below phone
                    .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = AppColors.color_white)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 96.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = AppColors.color_white)
                    )
                    Text(
                        text = "Male",
                        color = AppColors.color_900,
                        style = AppTypes.type_Body_Large_400.copy(letterSpacing = (-0.33).sp),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .offset(x = 0.dp, y = (-0.5).dp)
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = "Gender",
                    color = AppColors.color_900,
                    style = AppTypes.type_Typography_Label_Large.copy(letterSpacing = (-0.25).sp),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .offset(x = 0.dp, y = 0.dp)
                        .fillMaxWidth()
                )
            }

        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 778.dp)
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 34.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .offset(x = 0.5.dp, y = (-9).dp)
                    .requiredWidth(width = 134.dp)
                    .requiredHeight(height = 5.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
                    .background(color = AppColors.color_black)
            )
        }
    }
}

@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun InstagramProfileEditPreview() {
    InstagramProfileEdit(Modifier)
}